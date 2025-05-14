package distributed.order.management

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DistributedOrderManagementApplication

fun main(args: Array<String>) {
	runApplication<DistributedOrderManagementApplication>(*args)
}
