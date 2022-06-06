package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {

    // EMF는 서버 구동시 단 1개의 객체만 생성하고
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    // Transaction을 얻어오고
    EntityTransaction tx = em.getTransaction();
    // tx 시작
    tx.begin();
    try {
      //      Member findMember = em.find(Member.class, 1L);

      // JPQL은 쿼리가 테이블이 대상이 아니라 객체가 대상이다.
      List<Member> result =
          em.createQuery("select m from Member as m", Member.class)
              // JPQL은 객체에 맞추는 객체 지향 쿼리로 방언 셋팅에 맞게 실제 실행 쿼리가 변경된다.
              .setFirstResult(5)
              .setMaxResults(8)
              .getResultList();

      for (Member member : result) {
        System.out.println("member.name = " + member.getName());
      }

      // Transaction commit
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      // DB 커넥션을 닫는다.
      em.close();
    }
    emf.close();
  }
}
