package fr.mbds.reddit

class Community {

    String name
    String description
    CustomFile banner
    CustomFile thumbnail
    Date dateCreated
    Date lastUpdated
    User author

    static hasMany = [posts: Post, members: User]
    static belongsTo = User

    static constraints = {
        name nullable: false, blank: false, unique: true, maxSize: 21
        description nullable: true, blank: true
        banner nullable: false
        thumbnail nullable: false
    }
}
