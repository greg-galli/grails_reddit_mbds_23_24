<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-user" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list user">

        <li class="fieldcontain">
            <span id="username-label" class="property-label">Username</span>

            <div class="property-value" aria-labelledby="username-label">
                <g:fieldValue bean="${user}" field="username"/>
            </div>
        </li>

        <li class="fieldcontain">
            <span id="email-label" class="property-label">Email</span>

            <div class="property-value" aria-labelledby="email-label">
                <g:fieldValue bean="${user}" field="email"/>
            </div>
        </li>

        <li class="fieldcontain">
            <span id="bio-label" class="property-label">Bio</span>

            <div class="property-value" aria-labelledby="bio-label">
                <g:fieldValue bean="${user}" field="bio"/>
            </div>
        </li>

        <g:if test="${user.thumbnail}">
            <li class="fieldcontain">
                <span id="thumbnail-label" class="property-label">Thumbnail</span>
                <img src="${grailsApplication.config.customFile.url + user.thumbnail.name}" alt="user thumb" title="user thumb"/>
                %{--                    <asset:image src="${user.thumbnail.name}" />--}%
                %{--                    Votre propre taglib ici -> permets de gérer les différents types de fichier--}%
            </li>
        </g:if>

        <li class="fieldcontain" style="display: flex;">
            <span id="communities-label" class="property-label">Communities</span>
            <ul style="margin-left: 20px">
                <g:each in="${user.communities}" var="com">
                    <li><g:link controller="community" action="show" id="${com.id}">
                        <g:fieldValue bean="${com}" field="name"/>
                    </g:link></li>
                </g:each>
            </ul>
        </li>

        <li class="fieldcontain">
            <span id="files-label" class="property-label">Files</span>
            <div class="property-value" aria-labelledby="files-label">
                <g:each in="${user.files}" var="file">
                    <img src="${grailsApplication.config.customFile.url + file.name}" />
                </g:each>
            </div>
        </li>

    </ol>
    <g:form resource="${this.user}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.user}"><g:message code="default.button.edit.label"
                                                                                  default="Edit"/></g:link>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
