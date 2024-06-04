package kr.kro.calcking.calckingwebbe.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import kr.kro.calcking.calckingwebbe.documents.TokenDocument;

public interface TokenRepository extends MongoRepository<TokenDocument, String>, TokenRepositoryCustom {
  // CREATE
  public void createToken(String refreshToken, Date expireAt, String uID);

  // READ
  public Optional<TokenDocument> readToken(String refreshToken);

  // UPDATE
  public void updateToken(String prevRefreshToken, String nextRefreshToken, Date expireAt);

  // DELETE
  public void deleteToken(String refreshToken);

  public void deleteTokenByUID(String uID);
}
