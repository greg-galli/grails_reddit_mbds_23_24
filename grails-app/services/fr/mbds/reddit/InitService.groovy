package fr.mbds.reddit

import grails.gorm.transactions.Transactional

@Transactional
class InitService {

    def init() {
        if (User.count == 0) {
            // Création des Roles qui serviront à exploiter la plateforme
            def adminRole = new Role(authority: "ROLE_ADMIN").save()
            def userRole = new Role(authority: "ROLE_USER").save()
            // Création du compte User admin
            def adminUser = new User(username: "admin", password: "admin", email: "reddit@mbds.fr").save()
            UserRole.create(adminUser, adminRole, true)

            // Création de 5 Users
            ["Yahya", "Abdenour", "Ayoub", "Sebastien", "Elodie"].each {
                String username ->
                    def userInstance = new User(username: username, password: "password", email: username + "@univ-cotedazur.fr").save()
                    // On leur attribue le rôle "User"
                    UserRole.create(userInstance, userRole)
                    userInstance.save(flush: true)
            }
            User.withSession {
                it.flush()
                it.clear()
            }
        }
        // Pour chaque User, on crée une Community
        if (Community.count == 0) {
            User.list().each {
                User userInstance ->
                    new Community(name: userInstance.username + "'s community",
                            description: "Une belle description",
                            banner: new CustomFile(name: "grails.svg", type: CustomFileType.IMG, author: userInstance),
                            thumbnail: new CustomFile(name: "grails.svg", type: CustomFileType.IMG, author: userInstance),
                            author: userInstance).save(flush: true)
            }

            // Chaque User devient membre de chaque Community
            User.list().each {
                User user ->
                    Community.list().each {
                        Community community ->
                            user.addToCommunities(community)
                    }
                    user.save(flush: true)
            }

            User.withSession {
                it.flush()
                it.clear()
            }
            Community.withSession {
                it.flush()
                it.clear()
            }
        }
        // On rajoute deux Post pour chaque User dans chaque Community
        if (Post.count == 0)
        {
            User.list().each {
                User userInstance ->
                    Community.list().each {
                        Community communityInstance ->
                            ["Coucou","C'est moi !"].each {
                                def postInstance = new Post(title: it, author: userInstance, content: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." )
                                communityInstance.addToPosts(postInstance)
                            }
                            communityInstance.save(flush: true)
                    }
            }
            Post.withSession {
                it.flush()
                it.clear()
            }
        }
        // On rajoute un message par Post par User
        if (Message.count == 0)
        {
            Post.list().each {
                Post postInstance ->
                    User.list().each {
                        User userInstance ->
                            postInstance.addToMessages(new Message(content: "Ceci est le contenu du message de $userInstance.username", author: userInstance))
                    }
                    postInstance.save(flush: true)
            }

            Message.withSession {
                it.flush()
                it.clear()
            }
        }
        return true
    }
}
