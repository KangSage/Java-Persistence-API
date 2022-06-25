package hellojpa;

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
      member.setName("AAAAA");

      // 준영속 - EM을 닫는다.
      em.close();
      System.out.println("========= Atfer em.close ==========");

      // em이 닫혀서 조회가 실행되지 않는다.
      Member member2 = em.find(Member.class, 150L);

      System.out.println(" ===================");
      tx.commit(); // 커밋할 것이 없다.
    } catch (Exception e) {
      tx.rollback();
    } finally {
      // DB 커넥션을 닫는다.
      em.close();
    }
    emf.close();
  }
}
