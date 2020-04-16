package kr.pe.advenoh.repository;

import com.querydsl.jpa.JPQLQuery;
import kr.pe.advenoh.model.Member;
import kr.pe.advenoh.model.QMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Slf4j
public class MemberSupportRepositoryImpl extends QuerydslRepositorySupport implements MemberSupportRepository {
    public MemberSupportRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public List<Member> getAllMembers() {
        //		Member member = new Member("sdf");

        //		return Arrays.asList(member);
        QMember member = QMember.member;

        JPQLQuery jpqlQuery = from(member);
        List<Member> fetch = jpqlQuery.fetch();

        return fetch;
    }
}
