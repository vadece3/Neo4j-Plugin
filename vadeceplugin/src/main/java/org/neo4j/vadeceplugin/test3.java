package org.neo4j.vadeceplugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.neo4j.graphdb.*;
import org.neo4j.procedure.*;


public class test3 {
    @Context
    public Transaction tx;


    @UserFunction
    @Description("org.neo4j.vadeceplugin.actors(movietitle) - returns the movie name by movie id")
    public List<String> actors(@Name("movietitle") String movietitle) {
        if (movietitle == null) {
            return null;
        }
        String query = "MATCH (p:Person)-[r:ACTED_IN]->(m:Movie) WHERE m.title =$movietitle RETURN p.name";
        Result result = tx.execute(query, Map.of("movietitle", movietitle));
        List<String> results= new ArrayList<>();
        if (result.hasNext()) {
            while(result.hasNext()){
               results.add((String) result.next().get("p.name"));
            }
                return results;

        } else {
            return null;
        }
    }
}



