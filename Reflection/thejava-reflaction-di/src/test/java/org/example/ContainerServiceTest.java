package org.example;

import org.junit.Assert;
import org.junit.Test;

public class ContainerServiceTest {

    @Test
    public void getObject_Repository() {
        Repository repository = ContainerService.getObject(Repository.class);
        Assert.assertNotNull(repository);
    }

    @Test
    public void getObject_Service() {
        Service service = ContainerService.getObject(Service.class);
        Assert.assertNotNull(service);
        Assert.assertNotNull(service.repository);
    }

}