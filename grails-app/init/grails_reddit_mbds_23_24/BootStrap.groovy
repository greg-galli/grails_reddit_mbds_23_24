package grails_reddit_mbds_23_24

import fr.mbds.reddit.Role
import fr.mbds.reddit.User
import fr.mbds.reddit.UserRole

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: "ROLE_ADMIN").save()

        ["Yahya","Abdenour","Ayoub","Sebastien","Elodie"].each {
            def userInstance = new User(username: it, password: "password", email: it+"@unice.fr").save(flush: true, failOnError: true)
            UserRole.create(userInstance, adminRole, true)
        }
    }
    def destroy = {
    }
}
