package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();

    tx.begin();
    try {
      Member member = em.find(Member.class, 150L);

      // em.persist()를 호출하지 않아도 영속성 컨텍스트에서 자동으로 저장된다.
      member.setName("ZZZZZ");

      // 아래아 같은 코드가 없어도 엔티티 객체의 값이 변경되면 커밋 될 때 자동으로 저장된다.
      // if (member.getName().equals("ZZZZZ")) {
      //   em.update(member);
      // }

      // em.persist(member);

      // Dirty Checking으로 변경점 자동 업데이트 쿼리 실행
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
