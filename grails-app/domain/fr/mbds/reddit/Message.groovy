package fr.mbds.reddit

class Message {

    String content
    Integer score = 0
    Date dateCreated
    Date lastUpdated
    User author

    static belongsTo = [post: Post]

    static constraints = {
        content nullable: false, blank: false
        score nullable: false
    }

    static mapping = {
        content type: 'text'
    }
}
