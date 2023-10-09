//package Logic;
//
//import com.cgvsu.protocurvefxapp.ProtoCurveApplication;
//import javafx.geometry.Point2D;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.paint.Color;
//
//import java.util.ArrayList;
//
//public class LagrangeInterpolation {
//    static public void drawLagrangeCurve(GraphicsContext graphicsContext, Point2D[] points) {
//        graphicsContext.setStroke(Color.BLUE);
//        graphicsContext.setLineWidth(2.0);
//
//        for (int i = 0; i < points.length - 1; i++) {
//            Point2D p0 = points[i];
//            Point2D p1 = points[i + 1];
//
//            double step = 0.001;
//            for (double t = 0; t <= 1; t += step) {
//                double x = (1 - t) * p0.getX() + t * p1.getX();
//                double y = lagrangeInterpolation(t, p0, p1);
//                graphicsContext.strokeRect(x, y, 1, 1);
//            }
//        }
//    }
//
//    static public double lagrangeInterpolation(double t, Point2D p0, Point2D p1) {
//        double x0 = p0.getX();
//        double y0 = p0.getY();
//        double x1 = p1.getX();
//        double y1 = p1.getY();
//
//        double result = (1 - t) * y0 + t * y1;
//
//        return result;
//
//    }
//}
