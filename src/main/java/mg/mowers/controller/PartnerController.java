package mg.mowers.controller;

import mg.mowers.entity.Partner;
import mg.mowers.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping
    public List<Partner> getAllPartners() {
        return partnerService.getAllPartners();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partner> getPartnerById(@PathVariable Long id) {
        Partner partner = partnerService.getPartnerById(id);
        if (partner == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(partner);
    }

    @PostMapping
    public Partner createPartner(@RequestBody Partner partner) {
        return partnerService.savePartner(partner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partner> updatePartner(@PathVariable Long id, @RequestBody Partner partnerDetails) {
        Partner partner = partnerService.getPartnerById(id);
        if (partner == null) {
            return ResponseEntity.notFound().build();
        }
        partner.setName(partnerDetails.getName());
        partner.setType(partnerDetails.getType());
        partner.setAddress(partnerDetails.getAddress());
        partner.setUserId(partnerDetails.getUserId());

        Partner updatedPartner = partnerService.savePartner(partner);
        return ResponseEntity.ok(updatedPartner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        Partner partner = partnerService.getPartnerById(id);
        if (partner == null) {
            return ResponseEntity.notFound().build();
        }
        partnerService.deletePartner(id);
        return ResponseEntity.noContent().build();
    }
}
