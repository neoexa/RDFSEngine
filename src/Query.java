import java.util.ArrayList;

public class Query {
    ArrayList <Condition> conditions;

    public Query(ArrayList<Condition> String) {
        super();
        this.conditions = query;
    }

    public Query() {
        super();
        this.conditions = new ArrayList<>();
    }

    public void addCondition(Condition c) {
        this.conditions.add(c);
    }

    @Override
    public String toString() {
        String toS = "#";
        for(Condition c : query) {
            toS += query.toString();
        }
        return toS;
    }

    public ArrayList<Condition> getListCondition() {
        return conditions;
    }

    public void setListCondition(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }


}
