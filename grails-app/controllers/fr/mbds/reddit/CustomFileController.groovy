package fr.mbds.reddit

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CustomFileController {

    CustomFileService customFileService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond customFileService.list(params), model:[customFileCount: customFileService.count()]
    }

    def show(Long id) {
        respond customFileService.get(id)
    }

    def create() {
        respond new CustomFile(params)
    }

    def save(CustomFile customFile) {
        if (customFile == null) {
            notFound()
            return
        }

        try {
            customFileService.save(customFile)
        } catch (ValidationException e) {
            respond customFile.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'customFile.label', default: 'CustomFile'), customFile.id])
                redirect customFile
            }
            '*' { respond customFile, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond customFileService.get(id)
    }

    def update(CustomFile customFile) {
        if (customFile == null) {
            notFound()
            return
        }

        try {
            customFileService.save(customFile)
        } catch (ValidationException e) {
            respond customFile.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'customFile.label', default: 'CustomFile'), customFile.id])
                redirect customFile
            }
            '*'{ respond customFile, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        customFileService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'customFile.label', default: 'CustomFile'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'customFile.label', default: 'CustomFile'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
