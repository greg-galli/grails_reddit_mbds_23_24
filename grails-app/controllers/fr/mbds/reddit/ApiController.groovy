package fr.mbds.reddit

import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class ApiController {

    def userService
    def userCustomService

    /**
     * Ressource SINGLETON USER
     * Répondra à /api/user sur tous les verbes utiles
     * Methodes : GET / PUT / PATCH / DELETE
     */
    def user() {
        if (!params.id)
            return response.status = 400
        User userInstance = userService.get(params.id)
        if (!userInstance)
            return response.status = 404
        switch (request.getMethod()) {
            case "GET":
                renderThis(userInstance, request.getHeader("Accept"))
                break
            case "PUT":
                break
            case "PATCH":
                break
            case "DELETE":
                userCustomService.deleteUserClean(userInstance)
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }

    /**
     * Ressource Collection USER
     * Gestion de GET / POST
     */
    def users() {
        switch (request.getMethod()) {
            case "POST":
                if (!request.getHeader("Content-Type").contains("json"))
                    render(status: 400, text: "Le body doit être formaté en JSON")
                def userInstance = new User(request.getJSON())
                if (userInstance.validate()) {
                    userService.save(userInstance)
                    return response.status = 201
                }
                render(status: 400, text: "Une erreur est survenue pendant la sauvegarde de l'utilisateur")
                break
            case "GET":
                break
            default:
                return response.status = 405
                break
        }
        return response.status = 406

    }

    def renderThis(Object object, String accept) {
        switch (accept) {
            case "json":
            case "application/json":
            case "text/json":
                render object as JSON
                break
            case "xml":
            case "application/xml":
            case "text/xml":
                render object as XML
                break
            default:
                render(status: 400, text: "L'encodage demandé n'est pas supporté")
                break
        }

    }
}
