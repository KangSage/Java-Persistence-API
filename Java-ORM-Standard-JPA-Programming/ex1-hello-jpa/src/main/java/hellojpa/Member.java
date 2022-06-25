package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity 클래스에는 public or protected 기본 생성자가 필수<br>
 * - JPA가 리플렉션 API, Proxy 기술 등을 활용하여 객체의 기본 생성자를 사용한다.<br>
 * - final 클래스, enum, interface, inner 클래스는 사용 불가 - 저장할 필드에 final 사용 X<br>
 */
@Entity // JPA가 관리하는 객체 - DB 테이블과 매핑할 클래스에는 필수
public class Member {
  @Id
  private Long id;

  // DDL 생성 기능 - JPA 실행 로직에는 영향 주지 않는다.
  // length 등은 validation으로 동작은 한다.
  @Column(unique = true, length = 10)

  private String name;

  private int age;

  public Member() {}

  public Member(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
