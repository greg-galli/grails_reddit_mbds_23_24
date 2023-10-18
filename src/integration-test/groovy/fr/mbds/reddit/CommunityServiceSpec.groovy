package fr.mbds.reddit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CommunityServiceSpec extends Specification {

    CommunityService communityService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Community(...).save(flush: true, failOnError: true)
        //new Community(...).save(flush: true, failOnError: true)
        //Community community = new Community(...).save(flush: true, failOnError: true)
        //new Community(...).save(flush: true, failOnError: true)
        //new Community(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //community.id
    }

    void "test get"() {
        setupData()

        expect:
        communityService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Community> communityList = communityService.list(max: 2, offset: 2)

        then:
        communityList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        communityService.count() == 5
    }

    void "test delete"() {
        Long communityId = setupData()

        expect:
        communityService.count() == 5

        when:
        communityService.delete(communityId)
        sessionFactory.currentSession.flush()

        then:
        communityService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Community community = new Community()
        communityService.save(community)

        then:
        community.id != null
    }
}
