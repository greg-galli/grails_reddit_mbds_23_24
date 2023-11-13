package fr.mbds.reddit

import grails.gorm.transactions.Transactional

@Transactional
class UserCustomService {

    def deleteUserClean(User user)
    {
        def memberOfCommunitiesIds = user.communities*.id.toList()
        memberOfCommunitiesIds.each {
            Long id ->
                user.removeFromCommunities(Community.get(id))
        }

//        def communities = Community.findAllByAuthor(user)
//        communities.each {
//            it.delete()
//        }


        user.delete()
    }
}
