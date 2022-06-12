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
      Member foundMember1 = em.find(Member.class, 101L);
      // 캐시에서 조회 - 쿼리는 1번
      Member foundMember2 = em.find(Member.class, 101L);
      // 1차 캐시에서 동일성을 보장하므로 == 비교시 true를 리턴, equals도 true를 리턴
      System.out.println("result = " + (foundMember1 == foundMember2));
      System.out.println("equals result = " + (foundMember1.equals(foundMember2)));
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
