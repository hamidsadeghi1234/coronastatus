package ir.Hs.coronastatus.classes;

import android.content.Context;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class PieGraph {
    public GraphicalView graphicalView(Context context, int largeSlide , int mediumSlide,int smallSlide){
        CategorySeries series=new CategorySeries("yummy pie");
        int[] portions={largeSlide,mediumSlide,smallSlide};
        String[] seriesName=new String[] {"بزرگ ","متوسط","کوچک"};
        int numSlide=3;
        for (int i=0;i<numSlide;i++) {series.add("اندازه" +seriesName[i],portions[i] ); }
        DefaultRenderer defaultRenderer=new DefaultRenderer();
        SimpleSeriesRenderer simpleSeriesRenderer=null;
        int[] colors={Color.BLUE,Color.RED,Color.GREEN};
        for (int i=0;i<numSlide;i++) {
            simpleSeriesRenderer= new SimpleSeriesRenderer();
            simpleSeriesRenderer.setColor(colors[i]);
            defaultRenderer.addSeriesRenderer(simpleSeriesRenderer);}
        return ChartFactory.getPieChartView(context,series,defaultRenderer);
    }
}
