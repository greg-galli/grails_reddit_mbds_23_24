package fr.mbds.reddit

enum CustomFileType {
    IMG ("IMG"),
    VID ("VID"),
    OTHER ("OTHER")

    String type

    CustomFileType(String type)
    {
        this.type = type
    }

}