package at.bayava.acme.productinventory.db.repo

import at.bayava.acme.test.SpringBaseSpec
import io.kotlintest.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.interceptor.SimpleKey
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import javax.cache.CacheManager
import javax.cache.configuration.MutableConfiguration
import javax.cache.expiry.CreatedExpiryPolicy
import javax.cache.expiry.Duration


@ContextConfiguration(classes = [CachingConfigTest.TestC::class])
class CachingConfigTest : SpringBaseSpec() {

    class TestC(cacheManager: CacheManager) {

        init {
            setupCache(cacheManager)
        }

        @Bean
        fun cacheingBean(): CachingBean {
            return CachingBean(0)
        }

        private fun setupCache(cacheManager: CacheManager) {
            val configuration = MutableConfiguration<SimpleKey, Integer>()
                .setTypes(SimpleKey::class.java, Integer::class.java)
                .setStoreByValue(false)
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE))
            cacheManager.createCache("testCache", configuration)
        }
    }

    @Autowired
    lateinit var cachingBean: CachingBean

    @Test
    fun initialDbShouldBeEmpty() {

        val cached = cachingBean.countCached()
        cachingBean.count = 500

        cachingBean.countCached() shouldBe cached
    }

}


open class CachingBean(var count: Int = 0) {

    @Cacheable(cacheNames = ["testCache"])
    open fun countCached(): Int {
        return count
    }

}