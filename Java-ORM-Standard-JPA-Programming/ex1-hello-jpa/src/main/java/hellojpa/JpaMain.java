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

      Member member = new Member();
      member.setId(101L);
      member.setName("HelloJPA");

      System.out.println("=== BEFORE ===");
      em.persist(member);
      System.out.println("=== AFTER ===");

      // select 쿼리가 나가지 않고
      Member foundMember = em.find(Member.class, 101L);

      System.out.println("foundMember.id = " + foundMember.getId());
      System.out.println("foundMember.name = " + foundMember.getName());

      // insert 쿼리 전송
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
