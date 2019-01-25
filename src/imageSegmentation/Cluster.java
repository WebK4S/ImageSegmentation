package imageSegmentation;


import java.util.Set;

public class Cluster<SCALE extends ColorScale> {

    private Pixel<SCALE> centerPixel;
    private Set<Pixel<SCALE>> belongingPixels;

    public boolean reevaluateCenterPixel() {
        double bestCandidatesMeasure = Double.MAX_VALUE;
        Pixel<SCALE> bestCandidate = null;

        for (Pixel<SCALE> candidateCenter : belongingPixels) {
            double sum = 0;
            for (Pixel<SCALE> memberPixel : belongingPixels) {
                if (memberPixel.equals(candidateCenter)) {
                    continue;
                }
                sum += candidateCenter.distanceFrom(memberPixel);
            }
            if (sum < bestCandidatesMeasure) {
                bestCandidatesMeasure = sum;
                bestCandidate = candidateCenter;
            }
        }
        if (bestCandidate.equals(centerPixel)) {
            return false;
        }
        centerPixel = bestCandidate;
        return true;
    }

    public void addPixel(Pixel<SCALE> newPixel) {
        this.belongingPixels.add(newPixel);
    }

    public void removePixel(Pixel<SCALE> newPixel) {
        this.belongingPixels.remove(newPixel);
    }

    /**
     *
     * @param pixel
     * @return distance from center of cluster to pixel
     */
    public double distanceFrom(Pixel<SCALE> pixel) {
        return this.centerPixel.distanceFrom(pixel);
    }
}
