package hello.studyWithGrade.service;

import hello.studyWithGrade.entity.Study;
import hello.studyWithGrade.entity.manytomany.StudyMember;
import hello.studyWithGrade.entity.user.User;
import hello.studyWithGrade.repository.StudyMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyMemberService {

    private final StudyMemberRepository studyMemberRepository;

    public void save(StudyMember studyMember) {

        studyMemberRepository.save(studyMember);
    }

    /*
     * 회원 탈퇴 시, 스터티에서 회원에 대한 정보 삭제
     * 회원 탈퇴 처리 전에 먼저 시행된다(스터디와의 유저 관계를 먼저 해제하기 위해)
     */
    public void deleteMember(List<StudyMember> studyMembers) {

        studyMemberRepository.deleteAllInBatch(studyMembers);
    }

    /*
     * 스터디장 탈퇴 시, 스터디 자체에 대한 정보 삭제
     */
    public void deleteStudy(List<StudyMember> studyMembers) {

        studyMemberRepository.deleteAllInBatch(studyMembers);
    }

    public List<StudyMember> findByUser(User user) {

        return studyMemberRepository.findByUser(user);
    }

    public List<StudyMember> findByStudy(Study study) {

        return studyMemberRepository.findByStudy(study);
    }
}