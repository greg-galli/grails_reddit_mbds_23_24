package fr.mbds.reddit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CustomFileServiceSpec extends Specification {

    CustomFileService customFileService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CustomFile(...).save(flush: true, failOnError: true)
        //new CustomFile(...).save(flush: true, failOnError: true)
        //CustomFile customFile = new CustomFile(...).save(flush: true, failOnError: true)
        //new CustomFile(...).save(flush: true, failOnError: true)
        //new CustomFile(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //customFile.id
    }

    void "test get"() {
        setupData()

        expect:
        customFileService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CustomFile> customFileList = customFileService.list(max: 2, offset: 2)

        then:
        customFileList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        customFileService.count() == 5
    }

    void "test delete"() {
        Long customFileId = setupData()

        expect:
        customFileService.count() == 5

        when:
        customFileService.delete(customFileId)
        sessionFactory.currentSession.flush()

        then:
        customFileService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CustomFile customFile = new CustomFile()
        customFileService.save(customFile)

        then:
        customFile.id != null
    }
}
