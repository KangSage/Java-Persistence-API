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
      member.setName("AAAAA");

      // 준영속 - EM 초기화
      em.clear();

      // select 쿼리가 1번 더 실행된다
      Member member2 = em.find(Member.class, 150L);

      System.out.println("===================");
      tx.commit(); // update 쿼리가 실행되지 않는다.
    } catch (Exception e) {
      tx.rollback();
    } finally {
      // DB 커넥션을 닫는다.
      em.close();
    }
    emf.close();
  }
}
