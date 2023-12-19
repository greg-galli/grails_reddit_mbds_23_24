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
        def communities = Community.findAllByAuthor(user)
        communities.each{
            Community community ->
                def communityMembers = community.members*.id
                communityMembers.each {
                    Long communityMemberId ->
                        User.get(communityMemberId).removeFromCommunities(community)
                }
                community.delete()
        }
        def userFiles = CustomFile.findAllByAuthor(user)
        userFiles.each {it.delete()}
        def messages = Message.findAllByAuthor(user)
        messages.each {
            it.delete()
        }
        def posts = Post.findAllByAuthor(user)
        posts.each {
            it.delete()
        }
        UserRole.removeAll(user)
        user.delete()
    }
}
