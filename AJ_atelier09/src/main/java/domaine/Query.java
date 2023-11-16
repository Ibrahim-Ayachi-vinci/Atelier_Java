package domaine;

public class Query {

    private String url;
    private QueryMethod method;


    public Query (String url, QueryMethod method) {
        this.url = url;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public QueryMethod getMethod() {
        return method;
    }

    public enum QueryMethod{
        GET, POST
    }

}
