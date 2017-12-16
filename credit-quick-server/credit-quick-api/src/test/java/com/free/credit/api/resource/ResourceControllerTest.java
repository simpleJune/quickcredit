package com.free.credit.api.resource;

import java.util.Map;

import org.junit.Test;

import com.free.credit.api.resource.request.ResourceRequest;
import com.free.credit.test.BaseTest;


public class ResourceControllerTest extends BaseTest{


    @Test
    public void getStaticResource() throws Exception {
        ResourceRequest req = new ResourceRequest();
        remote("/resource/binCard", Map.class, req);
    }

}
