package edu.escuelaing.arep;

import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Service;

import static spark.Spark.*;
import static spark.Service.*;

public class App 
{
    public static void main( String[] args )
    {
        serverSqrt();
        serverSin();
    }

    static void serverSin() {
        Service http = ignite();
        http.get("/sin", (req, res) -> retornajson(req,res,"sin"));
    }

    static void serverSqrt() {
        Service http = ignite()
                .port(8080)
                .threadPool(20);

        http.get("/sqrt", (req, res) -> retornajson(req,res,"sqrt"));
    }

    public static JSONObject retornajson(Request request, Response response, String operation){
        JSONObject json = new JSONObject();
        double value = Double.parseDouble(request.queryParams("value"));
        json.put("Operation",operation);
        json.put("Input", value);
        if (operation.equals("sin")){
            json.put("Output",Operacion.sin(value));
        }
        else if(operation.equals("sqrt")){
            json.put("Output", Operacion.sqrt(value));
        }
        return json;

    }
}
