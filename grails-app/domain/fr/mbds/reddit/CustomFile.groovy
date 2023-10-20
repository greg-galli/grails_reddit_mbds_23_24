package fr.mbds.reddit

class CustomFile {

    String name
    CustomFileType type
    User author

    static constraints = {
        name nullable: false, blank: false
    }
}
