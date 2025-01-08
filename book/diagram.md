model {
u = person "User" "the user who uses the system"
q = person "Admin" "the person who manages the catalog"
s1 = softwareSystem "Software System" "the main system"{
wa = container "Web Application"{
tag "web"
}
wadm = container "Admin Application"{
tag "web"
}
users = container "Users service"{
tag "Service"
}
catalog = container "Catalog service"{
tag "Service"
}
orders = container "Order service"{
tag "Service"
}
db = container "Database Schema" {
tags "Database"
}
}

        u -> s1.wa "Uses"
        q -> s1.wadm "Uses to update"
        s1.wa -> s1.catalog "browse"
        s1.wa -> s1.orders "select course"
        s1.wa -> s1.users "subscribe"
        s1.catalog -> s1.db "Reads from and writes to"
        s1.users -> s1.db "Reads from and writes to"
        s1.orders -> s1.db "Reads from and writes to"

    }

    views {
        systemContext s1 "Diagram1" {
            include *
            autolayout lr
        }

        container s1 "Diagram2" {
            include *
            autolayout lr
        }

        styles {
            element "Element" {
                color #ffffff
            }
            element "Person" {
                background #741eba
                shape person
            }
            element "Software System" {
                background #8723d9
            }
            element "Container" {
                background #9a28f8
            }
             element "web" {
                background #872300
            }
            element "Service" {
                shape hexagon
            }
            element "Database" {
                shape cylinder
            }
        }
    }

    configuration {
        scope softwaresystem
    }

}