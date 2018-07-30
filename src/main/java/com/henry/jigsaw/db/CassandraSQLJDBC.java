package com.henry.jigsaw.db;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Metadata;

public class CassandraSQLJDBC {


    public static void main(String[] args) {

        Cluster cluster =
                Cluster.builder().addContactPoint("127.0.0.1").withPort(9042).build();

        Metadata metadata = cluster.getMetadata();
        for (Host host : metadata.getAllHosts()) {
            System.out.println("------" + host.getAddress());
        }

        System.out.println("======================");

        for (KeyspaceMetadata keyspaceMetadata : metadata.getKeyspaces()) {
            System.out.println("--------" + keyspaceMetadata.getName());
        }

        cluster.close();
    }
}
