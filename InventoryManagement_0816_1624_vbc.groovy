// 代码生成时间: 2025-08-16 16:24:57
// Import necessary Grails libraries and classes
import grails.transaction.Transactional
import org.springframework.validation.ValidationUtils

// Domain class for Inventory Item
class InventoryItem {
    String name
    Integer quantity
    static constraints = {
        name blank: false, maxSize: 255
        quantity min: 0
    }
}

// Service class for Inventory Management
@Transactional
class InventoryService {
    // Add a new inventory item
    def addItem(InventoryItem item) {
        if (item.validate()) {
            item.save(flush: true)
            return item
        } else {
            throw new RuntimeException('Validation error: ' + item.errors.toString())
        }
    }

    // Update an existing inventory item
    def updateItem(InventoryItem item) {
        if (item.validate()) {
            item.save(flush: true)
            return item
        } else {
            throw new RuntimeException('Validation error: ' + item.errors.toString())
        }
    }

    // Delete an inventory item by ID
    def deleteItem(Long id) {
        def item = InventoryItem.get(id)
        if (item) {
            item.delete(flush: true)
            return item
        } else {
            throw new RuntimeException('Item not found with ID: ' + id)
        }
    }

    // Get an inventory item by ID
    def getItem(Long id) {
        def item = InventoryItem.get(id)
        if (item) {
            return item
        } else {
            throw new RuntimeException('Item not found with ID: ' + id)
        }
    }

    // List all inventory items
    def listAllItems() {
        InventoryItem.list()
    }
}

// Controller class for Inventory Management
class InventoryController {
    def inventoryService

    def index() {
        redirect(action: 'list', params: params)
    }

    // List all inventory items
    def list() {
        if (params.id) {
            def item = inventoryService.getItem(params.id as Long)
            [inventoryItem: item]
        } else {
            [inventoryItems: inventoryService.listAllItems()]
        }
    }

    // Save a new inventory item
    def save() {
        def item = new InventoryItem(params)
        if (item.validate()) {
            item = inventoryService.addItem(item)
            flash.message = 'Item saved successfully'
            redirect(action: 'list', id: item.id)
        } else {
            render(view: 'create', model: [inventoryItem: item])
        }
    }

    // Show form for creating a new inventory item
    def create() {
        [inventoryItem: new InventoryItem(params)]
    }

    // Show form for editing an existing inventory item
    def edit() {
        def item = inventoryService.getItem(params.id as Long)
        [inventoryItem: item]
    }

    // Update an existing inventory item
    def update() {
        def item = inventoryService.getItem(params.id as Long)
        item.properties = params
        if (item.validate()) {
            item = inventoryService.updateItem(item)
            flash.message = 'Item updated successfully'
            redirect(action: 'list', id: item.id)
        } else {
            render(view: 'edit', model: [inventoryItem: item])
        }
    }

    // Delete an inventory item
    def delete() {
        def item = inventoryService.deleteItem(params.id as Long)
        flash.message = 'Item deleted successfully'
        redirect(action: 'list')
    }
}
