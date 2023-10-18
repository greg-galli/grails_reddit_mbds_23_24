package fr.mbds.reddit

class Post {

    String title
    String content
    Integer score = 0
    Date dateCreated
    Date lastUpdated
    User author

    static hasMany = [messages: Message, files: CustomFile]

    static belongsTo = [community: Community]

    static constraints = {
        title nullable: false, blank: false
        content nullable: false, blank: false
        score nullable: false
    }

    static mapping = {
        content type: 'text'
    }
}
