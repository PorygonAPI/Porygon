package br.com.porygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RelatorioBoxplot {
    private double q1;
    private double median;
    private double q3;
    private double lowerLimit;
    private double upperLimit;
    private List<Double> outliers;


    public RelatorioBoxplot(double q1, double median, double q3, double lowerLimit, double upperLimit, List<Double> outliers) {
        this.q1 = q1;
        this.median = median;
        this.q3 = q3;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.outliers = outliers;
    }

    public double getQ1() {
            return q1;
        }

        public void setQ1(double q1) {
            this.q1 = q1;
        }

        public double getMedian() {
            return median;
        }

        public void setMedian(double median) {
            this.median = median;
        }

        public double getQ3() {
            return q3;
        }

        public void setQ3(double q3) {
            this.q3 = q3;
        }

        public double getLowerLimit() {
            return lowerLimit;
        }

        public void setLowerLimit(double lowerLimit) {
            this.lowerLimit = lowerLimit;
        }

        public double getUpperLimit() {
            return upperLimit;
        }

        public void setUpperLimit(double upperLimit) {
            this.upperLimit = upperLimit;
        }

        public List<Double> getOutliers() {
            return outliers;
        }

        public void setOutliers(List<Double> outliers) {
            this.outliers = outliers;
        }

        public void RelatorioBoxplot(double q1, double median, double q3, double lowerLimit, double upperLimit, List<Double> outliers) {
            this.q1 = q1;
            this.median = median;
            this.q3 = q3;
            this.lowerLimit = lowerLimit;
            this.upperLimit = upperLimit;
            this.outliers = outliers;
        }


    public static RelatorioBoxplot calculateSummaryStatistics(double[] data) {
        // Ordenar os dados
        Arrays.sort(data);

        // Calcular os quartis
        double q1 = calculatePercentile(data, 25);
        double median = calculatePercentile(data, 50);
        double q3 = calculatePercentile(data, 75);

        // Calcular o intervalo interquartil (IQR)
        double iqr = q3 - q1;

        // Definir os limites inferior e superior
        double lowerLimit = q1 - 1.5 * iqr;
        double upperLimit = q3 + 1.5 * iqr;

        // Identificar outliers
        List<Double> outliers = new ArrayList<>();
        for (double value : data) {
            if (value < lowerLimit || value > upperLimit) {
                outliers.add(value);
            }
        }

        return new RelatorioBoxplot(q1, median, q3, lowerLimit, upperLimit, outliers);
    }

    private static double calculatePercentile(double[] data, double percentile) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("O conjunto de dados não pode ser vazio");
        }
        if (percentile < 0 || percentile > 100) {
            throw new IllegalArgumentException("O percentil deve estar entre 0 e 100");
        }

        int n = data.length;
        double rank = percentile / 100.0 * (n - 1);
        int lowerIndex = (int) Math.floor(rank);
        int upperIndex = (int) Math.ceil(rank);
        if (lowerIndex == upperIndex) {
            return data[lowerIndex];
        }
        double weight = rank - lowerIndex;
        return data[lowerIndex] * (1 - weight) + data[upperIndex] * weight;
    }
}

//    public static void main(String[] args) {
//        double[] data = {13.0, 25.0, 17.0, 28.0, 41.0, 33.0, 65.0, 42.0, 72.0, 69.0};
//        Boxplot summary = calculateSummaryStatistics(data);
//
//        System.out.println("Primeiro Quartil (Q1): " + summary.q1);
//        System.out.println("Mediana (Q2): " + summary.median);
//        System.out.println("Terceiro Quartil (Q3): " + summary.q3);
//        System.out.println("Limite Inferior: " + summary.lowerLimit);
//        System.out.println("Limite Superior: " + summary.upperLimit);
//        System.out.println("Outliers: " + summary.outliers);
//    }
