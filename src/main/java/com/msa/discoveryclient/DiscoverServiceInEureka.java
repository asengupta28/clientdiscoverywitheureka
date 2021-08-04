
/*	 *******************************************************************************	****
****																					****
**** This application uses org.springframework.cloud.client.discovery.DiscoveryClient	**** 
**** to dynamically call APIs by names that are registered with Eureka Server			**** 
****																					****
**** *******************************************************************************	***/

package com.msa.discoveryclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DiscoverServiceInEureka
{
	public static void main(String[] args) { SpringApplication.run(DiscoverServiceInEureka.class, args); }
}

@RestController
class ServiceInstanceRestController
{
	@Autowired
	private DiscoveryClient discoveryClient;

	// This method takes a Parameter - the value of the Parameter = Name in which an Application is registered in the Eureka Server
	// Look in Eureka Server's console and in the frame with tile "Instances currently registered with Eureka"
	// Pick any values from column title "Application" displayed below
	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName (@PathVariable String applicationName)
	{
		System.out.println("Executed");
		return this.discoveryClient.getInstances(applicationName);
	}
}
