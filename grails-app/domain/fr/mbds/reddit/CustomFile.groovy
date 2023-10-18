package fr.mbds.reddit

class CustomFile {

    String name
    String type
    User author

    static constraints = {
        name nullable: false, blank: false
        type inList: ["IMG", "VID", "OTHER"]
    }
}
