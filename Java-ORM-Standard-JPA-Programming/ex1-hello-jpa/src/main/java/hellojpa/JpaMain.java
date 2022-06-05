package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    // DB 커넥션을 얻어온다.
    EntityManager em = emf.createEntityManager();
    // Transaction을 얻어오고
    EntityTransaction tx = em.getTransaction();
    // tx 시작
    tx.begin();
    try {
      Member findMember = em.find(Member.class, 1L);
      System.out.println("findMember.id = " + findMember.getId());
      System.out.println("findMember.name = " + findMember.getName());

      em.remove(findMember);

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
