package grails_reddit_mbds_23_24

import fr.mbds.reddit.InitService

class BootStrap {

    InitService initService

    def init = { servletContext ->
        initService.init()
    }
    def destroy = {
    }
}
