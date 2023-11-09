package be.vinci.api;

import be.vinci.instances.InstanceGraph1;
import be.vinci.services.InstancesAnalyzer;
import jakarta.json.JsonStructure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

@Path("instances")
public class Instances {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonStructure getInstanceGraphInfo(@QueryParam("builderclassname") String builderClassname) {
        try {
            // Utiliser la réflexion pour obtenir la classe correspondante
            Class<?> builderClass = Class.forName("be.vinci.instances." + builderClassname);

            // Vérifier si la classe existe
            if (builderClass != null) {
                // Vérifier si la classe a une méthode initInstanceGraph()
                Method initInstanceGraphMethod = builderClass.getMethod("initInstanceGraph");

                if (initInstanceGraphMethod != null) {
                    // Créer une instance de la classe et appeler la méthode initInstanceGraph()
                    Object builder = builderClass.getDeclaredConstructor().newInstance();
                    Object instanceGraph = initInstanceGraphMethod.invoke(builder);

                    // Analyser le graphe d'instances
                    InstancesAnalyzer analyzer = new InstancesAnalyzer(instanceGraph);
                    return analyzer.getFullInfo();
                } else {
                    // La classe ne contient pas de méthode initInstanceGraph()
                    throw new InternalServerErrorException("La classe spécifiée ne contient pas de méthode initInstanceGraph.");
                }
            } else {
                // La classe spécifiée n'existe pas
                throw new NotFoundException("La classe spécifiée n'existe pas.");
            }
        } catch (ClassNotFoundException e) {
            // La classe spécifiée n'existe pas
            throw new NotFoundException("La classe spécifiée n'existe pas.");
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            // Erreur interne
            throw new InternalServerErrorException("Erreur interne lors de la récupération du graphe d'instances.");
        }
    }
}
