package hello;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

  @Bean(destroyMethod="shutdown")
  public net.sf.ehcache.CacheManager ehCacheManager() {
    CacheConfiguration cacheConfiguration = new CacheConfiguration();
    cacheConfiguration.setName("books");
    cacheConfiguration.setMaxEntriesLocalHeap(0);
    cacheConfiguration.setTimeToLiveSeconds(24*60*60);

    net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
    config.addCache(cacheConfiguration);

    return net.sf.ehcache.CacheManager.newInstance(config);
  }

  @Bean
  public CacheManager cacheManager() {
    return new EhCacheCacheManager(ehCacheManager());
  }

}
