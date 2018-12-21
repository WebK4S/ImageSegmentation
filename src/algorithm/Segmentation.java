package algorithm;

import entities.Cluster;

import java.util.Set;

public interface Segmentation {

    public Set<Cluster> prepareClusters();
    public Cluster createCluster();
}
