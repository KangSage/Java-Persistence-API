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

    // EMF는 서버 구동시 단 1개의 객체만 생성하고
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    // Transaction을 얻어오고
    EntityTransaction tx = em.getTransaction();
    // tx 시작
    tx.begin();
    try {
      // 객체를 생성하고 필드에 값을 할당하는 행위까지는 비영속
      Member member = new Member();
      member.setId(100L);
      member.setName("HelloJPA");

      // 영속 - 정확히는 이때 DB에 저장되진 않는다.
      System.out.println("=== BEFORE ===");
      em.persist(member);
      // 준영속 - 영속성 컨텍스트에서 지운다.
      em.detach(member);
      System.out.println("=== AFTER ===");

      // Transaction commit - 영속성 컨텍스트의 내용이 DB에 쿼리로 전송됨
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
