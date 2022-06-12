package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 영속성 컨텍스트의 이점
 * - DB와 어플리케이션 사이에 중간 계층을 둬서 여러가지의 이점이 생긴다.
 * - 버퍼링, 캐싱 등
 */
public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    // Transaction을 얻어오고
    EntityTransaction tx = em.getTransaction();
    // tx 시작
    tx.begin();
    try {
      // 영속
      Member member1 = new Member(150L, "A");
      Member member2 = new Member(160L, "B");

      // 여기서 각각 쿼리가 실행되지 않고 버퍼링 된다.
      em.persist(member1);
      em.persist(member2);

      System.out.println("=================");

      // 실제 쿼리는 1번에 모아서 실행
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
