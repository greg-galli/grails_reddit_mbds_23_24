package grails_reddit_mbds_23_24

import fr.mbds.reddit.InitService
import fr.mbds.reddit.Role
import fr.mbds.reddit.User
import fr.mbds.reddit.UserRole

class BootStrap {

    InitService initService

    def init = { servletContext ->
        initService.init()
    }
    def destroy = {
    }
}
