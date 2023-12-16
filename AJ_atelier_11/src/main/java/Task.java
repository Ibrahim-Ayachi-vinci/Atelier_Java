public class Task {
    private String titre;
    private String desciption;


    public boolean createTask (String titre, String desciption){
        if (titre == null || titre.equals("")){
            return false;
        }
        this.titre = titre;
        if (desciption == null){
            return false;
        }
        this.desciption = desciption;
        return true;
    }
}
