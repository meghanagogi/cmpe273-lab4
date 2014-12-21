package edu.sjsu.cmpe.cache.client;



public class Client2 {

    
    
    public static void main(String[] args) throws Exception {
         
        String valueRepaired="";
        String cache1Details="";
        String cache2Details="";
        String cache3Details="";
        System.out.println("Starting Cache Client...");
        CacheServiceInterface cache1 = new DistributedCacheService(
                "http://localhost:3000");
        CacheServiceInterface cache2 = new DistributedCacheService(
                "http://localhost:3001");
        CacheServiceInterface cache3 = new DistributedCacheService(
                "http://localhost:3002");
         System.out.println("Inserting into cache nodes");
         cache1.put(1, "a");
         cache2.put(1, "a");
         cache3.put(1, "a");
     
        System.out.println("Step1 completed");
        System.out.println("cache1  : " +cache1.get(1));
        System.out.println("cache2  : " +cache2.get(1));
        System.out.println("cache3  : " +cache3.get(1));
        System.out.println("Take server A down -- Sleep for 30 seconds");
        System.out.println("step 2 is getting intitalized");
        Thread.sleep(30000);
        System.out.println("cache nodes is getting inserted");
       
        cache1.put(1, "b");
        cache2.put(1, "b");
        cache3.put(1, "b");
        
        System.out.println("Step2 completed");
        System.out.println("Bring server A up -- Sleep for 30 seconds");
       Thread.sleep(30000);
        
        try
        {
        	cache1Details=cache1.get(1);
        	cache2Details=cache2.get(1);
        	cache3Details=cache3.get(1);
            
            
        }
        catch(Exception e)
        {
            System.out.println("Read the repaired values");
            cache1.put(1, "b");
            cache2.put(1, "b");
            cache3.put(1, "b");
            
       } 
        
       
       
        if(!cache1.get(1).toString().equals(cache2.get(1).toString())   && cache2.get(1).toString().equals(cache3.get(1).toString())){
            System.out.println("Read the repaired values");
            valueRepaired = cache2.get(1);
            
            cache1.put(1, valueRepaired);
        
           
       }
        
        else if(!cache1.get(1).equals(cache2.get(1))   && cache1.get(1).equals(cache3.get(1))  ){
          
           System.out.println("Read the repaired values");
           valueRepaired = cache1.get(1);
        
        cache2.put(1, valueRepaired);
        }
        
        else if(!cache3.get(1).equals(cache2.get(1))   && cache1.get(1).equals(cache2.get(1))  ){
          
           System.out.println("Read the repaired values");
           valueRepaired = cache1.get(1);
            
            cache3.put(1, valueRepaired);
         }
        
        
        
        
        
        System.out.println("cache1  : " +cache1.get(1));
        System.out.println("cache2  : " +cache2.get(1));
        System.out.println("cache3  : " +cache3.get(1));
      
       
              
        System.out.println("Current cache client");
    }
    
    
    

}