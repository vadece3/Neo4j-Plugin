package org.neo4j.vadeceplugin;

import java.util.Map;
import org.neo4j.graphdb.*;
import org.neo4j.procedure.*;


public class test2 {
    @Context
    public Transaction tx;


    @UserFunction
    @Description("org.neo4j.vadeceplugin.movieName(movieId) - returns the movie name by movie id")
    public String movieName(@Name("movieId") Long movieId) {
        if (movieId == null) {
            return null;
        }
        String query = "MATCH (m:Movie) WHERE m.id = $movieId RETURN m.title";
        Result result = tx.execute(query, Map.of("movieId", movieId));
        if (result.hasNext()) {
            return (String) result.next().get("m.title");
        } else {
            return null;
        }
    }
    }



