/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hellogeoip2;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class HelloGeoIP2 {



   

   public static void main(String[] args) throws IOException, GeoIp2Exception {
        
       // A File object pointing to your GeoLite2 database
       File dbFile = new File(MyConstants.DATABASE_CITY_PATH);
 
       // This creates the DatabaseReader object,
       // which should be reused across lookups.
 
       DatabaseReader reader = new DatabaseReader.Builder(dbFile).build();
 
       // A IP Address
       InetAddress ipAddress = InetAddress.getByName("81.64.96.216");
       System.out.println(ipAddress );
        
       // Get City info
       CityResponse response = reader.city(ipAddress);
 
       // Country Info
       Country country = response.getCountry();
       System.out.println("Country IsoCode: "+ country.getIsoCode()); // 'US'
       System.out.println("Country Name: "+ country.getName()); // 'United States'
//    System.out.println(country.getNames().get("zh-CN")); //
 //       System.out.println(country.getNames().get("Fr")); //
 
       Subdivision subdivision = response.getMostSpecificSubdivision();
       System.out.println("Subdivision Name: " +subdivision.getName()); // 'Minnesota'
       System.out.println("Subdivision IsoCode: "+subdivision.getIsoCode()); // 'MN'
 
       // City Info.
       City city = response.getCity();
       System.out.println("City Name: "+ city.getName()); // 'Minneapolis'
 
       // Postal info
       Postal postal = response.getPostal();
       System.out.println(postal.getCode()); // '55455'
 
       // Geo Location info.
       Location location = response.getLocation();
        
       // Latitude
       System.out.println("Latitude: "+ location.getLatitude()); // 44.9733
        
       // Longitude
       System.out.println("Longitude: "+ location.getLongitude()); // -93.2323
       
       System.out.println(response);
 
   }
}