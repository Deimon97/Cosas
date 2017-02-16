<%-- 
    Document   : obtener_grafico.jsp
    Created on : 08-feb-2017, 20:06:38
    Author     : dawbio
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page import="java.io.IOException"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="org.jfree.chart.plot.PlotOrientation"%>
<%@page import="java.io.OutputStream"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP Page</title>
    </head>
    <body>
<%@page import="org.jfree.*" %>

<%  // Create a simple Bar chart
    
    
        
        ArrayList value=(ArrayList)request.getAttribute("numGrafic");
        ArrayList key=(ArrayList)request.getAttribute("keyGrafic");

     DefaultPieDataset data = new DefaultPieDataset();
     for(int i=0;i<value.size();i++){       
        data.setValue((String)key.get(i),(int) value.get(i));
     }
        
 
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
         "Ejemplo Rapido de Grafico en un ChartFrame", 
         data, 
         true, 
         true, 
         false);
        try {
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ChartUtilities.writeChartAsPNG(os, chart, 625, 500);
 
 
        } catch (IOException e) {
            System.err.println("Error creando grafico.");
        }
%>
    </body>
</html>
