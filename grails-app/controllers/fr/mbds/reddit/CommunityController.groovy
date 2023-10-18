package fr.mbds.reddit

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CommunityController {

    CommunityService communityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond communityService.list(params), model:[communityCount: communityService.count()]
    }

    def show(Long id) {
        respond communityService.get(id)
    }

    def create() {
        respond new Community(params)
    }

    def save(Community community) {
        if (community == null) {
            notFound()
            return
        }

        try {
            communityService.save(community)
        } catch (ValidationException e) {
            respond community.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'community.label', default: 'Community'), community.id])
                redirect community
            }
            '*' { respond community, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond communityService.get(id)
    }

    def update(Community community) {
        if (community == null) {
            notFound()
            return
        }

        try {
            communityService.save(community)
        } catch (ValidationException e) {
            respond community.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'community.label', default: 'Community'), community.id])
                redirect community
            }
            '*'{ respond community, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        communityService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'community.label', default: 'Community'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'community.label', default: 'Community'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
