package kr.pe.advenoh.repository;


import kr.pe.advenoh.model.Member;

import java.util.List;

public interface MemberSupportRepository {
    List<Member> getAllMembers();
}
